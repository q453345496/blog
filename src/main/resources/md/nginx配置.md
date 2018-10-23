#代理
```
http {
    include       mime.types;
    default_type  application/octet-stream;
    proxy_temp_path /tmp/temp_dir;
    proxy_cache_path /tmp/cache levels=1:2 keys_zone=cache_one:100m inactive=1d max_size=10g;
    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    keepalive_timeout  65;

    #gzip  on;

    # another virtual host using mix of IP-, name-, and port-based configuration
    #
    server {
        listen       80;
        server_name  localhost;

        location / {
            proxy_pass http://172.30.16.70:8080;
            proxy_redirect    off;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header Host $host;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;

            proxy_http_version 1.1;
            proxy_set_header Upgrade $http_upgrade;
            proxy_set_header Connection "upgrade";

        }

        location = /favicon.ico {
                log_not_found off;
                access_log off;
        }

        location ~ .*\.(css|js|woff)(.*) {
                proxy_pass http://172.30.16.70:8080;
                proxy_redirect off;
                proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header Host $host;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                proxy_cache cache_one;
				proxy_cache_valid 200 304 1h ;
                expires 1h;
                add_header X-Cache $upstream_cache_status;
        }

    }
}
```