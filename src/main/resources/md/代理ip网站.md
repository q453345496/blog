```
[
    {
        'name': 'kuaidaili.com',
        'resource': ['https://www.kuaidaili.com/free/inha/%s' % i for i in range(1, 6)] +
                    ['https://www.kuaidaili.com/proxylist/%s' % i for i in range(1, 11)],
        'parse_rule': {
            'pre_extract_method': 'xpath',
            'pre_extract': '//tr',
            'infos_pos': 4,
            'infos_end': None,
            'detail_rule': 'td::text',
            'ip_pos': 0,
            'port_pos': 1,
            'extract_protocol': True,
            'split_detail': False,
            'protocols': None
        },
    },
    {
        'name': 'xicidaili.com',
        'resource': ['http://www.xicidaili.com/nn/%s' % i for i in range(1, 6)] +
                    ['http://www.xicidaili.com/wn/%s' % i for i in range(1, 6)] +
                    ['http://www.xicidaili.com/wt/%s' % i for i in range(1, 6)],
        'parse_rule': {
            'pre_extract_method': 'xpath',
            'pre_extract': '//tr',
            'infos_pos': 1,
            'infos_end': None,
            'detail_rule': 'td::text',
            'ip_pos': 0,
            'port_pos': 1,
            'extract_protocol': True,
            'split_detail': False,
            'protocols': None
        },
    },
    {
        'name': 'kxdaili.com',
        'resource': [
            'http://www.kxdaili.com/dailiip/%s/%s.html#ip' % (i, j) for i in range(1, 3) for j in range(1, 11)
        ],
        'parse_rule': {
            'pre_extract_method': 'xpath',
            'pre_extract': '//tr',
            'infos_pos': 1,
            'infos_end': None,
            'detail_rule': 'td::text',
            'ip_pos': 0,
            'port_pos': 1,
            'extract_protocol': True,
            'split_detail': False,
            'protocols': None
        },
    },
    {
        'name': 'mrhinkydink.com',
        'resource': ['http://www.mrhinkydink.com/proxies.htm'],
        'parse_rule': {
            'pre_extract_method': 'css',
            'pre_extract': '.text',
            'infos_pos': 1,
            'infos_end': None,
            'detail_rule': 'td::text',
            'ip_pos': 0,
            'port_pos': 1,
            'extract_protocol': True,
            'split_detail': False,
            'protocols': None
        },
    },
    {
        'name': 'nianshao.me',
        'resource': ['http://www.nianshao.me/?stype=1&page=%s' % i for i in range(1, 11)] +
                    ['http://www.nianshao.me/?stype=2&page=%s' % i for i in range(1, 11)] +
                    ['http://www.nianshao.me/?stype=5&page=%s' % i for i in range(1, 11)],
        'parse_rule': {
            'pre_extract_method': 'xpath',
            'pre_extract': '//tr',
            'infos_pos': 1,
            'infos_end': None,
            'detail_rule': 'td::text',
            'ip_pos': 0,
            'port_pos': 1,
            'extract_protocol': True,
            'split_detail': False,
            'protocols': None
        },
    },
    {
        'name': '66ip.cn',
        'resource': ['http://www.66ip.cn/%s.html' % i for i in range(1, 3)] +
                    ['http://www.66ip.cn/areaindex_%s/%s.html' % (i, j)
                     for i in range(1, 35) for j in range(1, 3)],
        'parse_rule': {
            'pre_extract_method': 'xpath',
            'pre_extract': '//tr',
            'infos_pos': 4,
            'infos_end': None,
            'detail_rule': 'td::text',
            'ip_pos': 0,
            'port_pos': 1,
            'extract_protocol': True,
            'split_detail': False,
            'protocols': None
        },
    },
    {
        'name': 'baizhongsou.com',
        'resource': ['http://ip.baizhongsou.com/'],
        'parse_rule': {
            'pre_extract_method': 'xpath',
            'pre_extract': '//tr',
            'infos_pos': 1,
            'infos_end': None,
            'detail_rule': 'td::text',
            'ip_pos': 0,
            'port_pos': 1,
            'extract_protocol': True,
            'split_detail': True,
            'protocols': None
        },
    },
    {
        'name': 'data5u.com',
        'resource': [
            'http://www.data5u.com/free/index.shtml',
            'http://www.data5u.com/free/gngn/index.shtml',
            'http://www.data5u.com/free/gwgn/index.shtml'
        ],
        'parse_rule': {
            'pre_extract_method': 'xpath',
            'pre_extract': '//ul[contains(@class, "l2")]',
            'infos_pos': 0,
            'infos_end': None,
            'detail_rule': 'span li::text',
            'ip_pos': 0,
            'port_pos': 1,
            'extract_protocol': True,
            'split_detail': False,
            'protocols': None
        },
    },
    {
        'name': 'ip181.com',
        'resource': ['http://www.ip181.com/'] +
                    ['http://www.ip181.com/daili/%s.html' % i for i in range(1, 20)],
        'parse_rule': {
            'pre_extract_method': 'xpath',
            'pre_extract': '//tr',
            'infos_pos': 1,
            'infos_end': None,
            'detail_rule': 'td::text',
            'ip_pos': 0,
            'port_pos': 1,
            'extract_protocol': True,
            'split_detail': False,
            'protocols': None
        },
    },
    {
        'name': 'ip3366.net',
        'resource': ['http://www.ip3366.net/free/?stype=1&page=%s' % i for i in range(1, 3)] +
                    ['http://www.ip3366.net/free/?stype=3&page=%s' % i for i in range(1, 3)],
        'parse_rule': {
            'pre_extract_method': 'xpath',
            'pre_extract': '//tr',
            'infos_pos': 1,
            'infos_end': None,
            'detail_rule': 'td::text',
            'ip_pos': 0,
            'port_pos': 1,
            'extract_protocol': True,
            'split_detail': False,
            'protocols': None
        },
    },
    {
        'name': 'iphai.com',
        'resource': [
            'http://www.iphai.com/free/ng',
            'http://www.iphai.com/free/wg',
            'http://www.iphai.com/free/np',
            'http://www.iphai.com/free/wp',
            'http://www.iphai.com/'
        ],
        'parse_rule': {
            'pre_extract_method': 'xpath',
            'pre_extract': '//tr',
            'infos_pos': 1,
            'infos_end': None,
            'detail_rule': 'td::text',
            'ip_pos': 0,
            'port_pos': 1,
            'extract_protocol': True,
            'split_detail': False,
            'protocols': None
        },
    },
    {
        'name': 'swei360.com',
        'resource': ['http://www.swei360.com/free/?page=%s' % i for i in range(1, 4)] +
                    ['http://www.swei360.com/free/?stype=3&page=%s' % i for i in range(1, 4)],
        'parse_rule': {
            'pre_extract_method': 'xpath',
            'pre_extract': '//tr',
            'infos_pos': 1,
            'infos_end': None,
            'detail_rule': 'td::text',
            'ip_pos': 0,
            'port_pos': 1,
            'extract_protocol': True,
            'split_detail': False,
            'protocols': None
        },
    },
    {
        'name': 'yundaili.com',
        'resource': [
            'http://www.yun-daili.com/free.asp?stype=1',
            'http://www.yun-daili.com/free.asp?stype=2',
            'http://www.yun-daili.com/free.asp?stype=3',
            'http://www.yun-daili.com/free.asp?stype=4',
        ],
        'parse_rule': {
            'pre_extract_method': 'xpath',
            'pre_extract': '//tr[contains(@class, "odd")]',
            'infos_pos': 0,
            'infos_end': None,
            'detail_rule': 'td::text',
            'ip_pos': 0,
            'port_pos': 1,
            'extract_protocol': True,
            'split_detail': False,
            'protocols': None
        },
    },
    {
        'name': 'ab57.ru',
        'resource': ['http://ab57.ru/downloads/proxyold.txt'],
        'parse_rule': {
            'pre_extract': None,
            'delimiter': '\r\n',
            'redundancy': None,
            'protocols': None
        },
    },
    {
        'name': 'proxylists.net',
        'resource': ['http://www.proxylists.net/http_highanon.txt'],
        'parse_rule': {
            'pre_extract': None,
            'delimiter': '\r\n',
            'redundancy': None,
            'protocols': None
        },
    },
    {
        'name': 'sslproxies.org/',
        'resource': ['https://www.sslproxies.org/'],
        'parse_rule': {
            'pre_extract_method': 'xpath',
            'pre_extract': '//tbody//tr',
            'infos_pos': 0,
            'infos_end': None,
            'detail_rule': 'td::text',
            'ip_pos': 0,
            'port_pos': 1,
            'extract_protocol': True,
            'split_detail': False,
            'protocols': None
        },
    },
    {
        'name': 'atomintersoft.com',
        'resource': [
            'http://www.atomintersoft.com/high_anonymity_elite_proxy_list',
            'http://www.atomintersoft.com/anonymous_proxy_list',
        ],
        'parse_rule': {
            'pre_extract_method': 'xpath',
            'pre_extract': '//tr',
            'infos_pos': 1,
            'infos_end': None,
            'detail_rule': 'td::text',
            'ip_pos': 0,
            'port_pos': 1,
            'extract_protocol': True,
            'split_detail': True,
            'protocols': None
        },
    },
    {
        'name': 'rmccurdy.com',
        'resource': [
            'https://www.rmccurdy.com/scripts/proxy/good.txt'
        ],
        'parse_rule': {
            'pre_extract': None,
            'delimiter': '\n',
            'redundancy': None,
            'protocols': None
        },
    },
]

```