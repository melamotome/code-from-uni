#!/usr/bin/python

'''
Написать собственный синонимизатор: каждое
существительное/прилагательное/глагол/наречие в
тексте заменяется на соответствующее слово-синоним
(requests+ParaPhraser API)
'''

import requests
import pymorphy2
import string
import re
morph = pymorphy2.MorphAnalyzer()

with open('text_to_syn.txt', 'r', encoding='utf8') as fin, open('text_to_syn_done.txt', 'w', encoding='utf8') as fout:
    lines = fin.readlines()
    for line in lines:
        for word in re.split('[' + string.punctuation + string.whitespace + ']+', line):
            if not word:
                continue
            else:
                payload = {
                    'c': 'syns',
                    'query': word,
                    'top': 1,
                    'scores': 0,
                    'forms': 0,
                    'format': 'json',
                    'lang': 'ru',
                    'token': 'bd50b5894e083ef43b5eb47aabbb21810a0e40cb'
                }
                r = requests.post('http://paraphraser.ru/api/', data=payload)
                result = r.json()

                if result['code'] == 0:
                    response = result['response']
                    for item in response:
                        for value in response[item]['syns']:
                            line = re.sub(word, value, line)
                        break #Беру только одно, потому что может быть несколько вариантов для слова(есть - быть или кушать)
                else:
                    print('Error:', result['msg'])
        fout.write(line)