#!/usr/bin/python

'''Запустить mystem на текстовом файле с любым
художественным произведением так, чтобы получить тэги
частей речи. Затем написать функцию, которая для
каждого предлога и союза в тексте вычисляет частоту их
встречаемости, вызвать ее и вывести полученные
частотные словари в отдельный текстовый файл.'''

import string
import re
from collections import defaultdict

splitter = re.compile('[=,]')
stripper = string.whitespace + string.punctuation
freqsCONJ = defaultdict(int)
freqsPR = defaultdict(int)
with open('TheSameBook_lemma.txt', 'r', encoding='UTF8') as fin:
    for line in fin:
        line = line.strip(stripper)
        if not line:
            continue
        items = re.split(splitter, line)
        if len(items) < 2:
            #print('Too short line:', line)
            continue
        lemma, pos = items[:2]
        if pos == 'CONJ':
            freqsCONJ[lemma.strip(stripper)] += 1
        if pos == 'PR':
            freqsPR[lemma.strip(stripper)] += 1



with open('TheSameBook_CONJ_PR_dict.txt', 'w', encoding='utf8') as fout:
    fout.write("CONJ:\n")
    for key, value in sorted(freqsCONJ.items(), key=lambda x: x[1], reverse=True):
        fout.write('{0} {1}\n'.format(key, value))

    fout.write("\nPR:\n")
    for key, value in sorted(freqsPR.items(), key=lambda x: x[1], reverse=True):
        fout.write('{0} {1}\n'.format(key, value))