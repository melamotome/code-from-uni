#!/usr/bin/python
'''Изменить программу из примера #2, чтобы получить на
выходе частотный словарь тэгов частей речи. По
полученному словарю рассчитать доли вхождения
разных частей речи в текст. Полученную статистику
вывести в отдельный файл, отсортировав тэги частей
речи по убыванию их доли в тексте.
Проинтерпретировать результат (что полученная
статистика может сказать об исходном тексте?).
Необходимо запустить программу на текстовом файле
объемом не менее 1 Мб, исходный текстовый файл
приложить к решению.'''
import string
import re
from collections import defaultdict

splitter = re.compile('[=,]')
stripper = string.whitespace + string.punctuation
freqs = defaultdict(int)
total = 0
with open('TheSameBook_lemma.txt', 'r', encoding='UTF8') as fin:
    for line in fin:
        line = line.strip(stripper)
        if not line:
            continue
        items = re.split(splitter, line)
        if len(items) < 2:
            #print('Too short line:', line)
            continue
        pos = items[1]
        freqs[pos] += 1
        total += 1

percents = defaultdict(int)
for pos in freqs:
    percents[pos] = freqs[pos] / total * 100

print(percents)

with open('TheSameBook_percents.txt', 'w', encoding='utf8') as fout:
    for key, value in sorted(percents.items(), key=lambda x: x[1], reverse=True):
        fout.write('{0} {1:.2f}%\n'.format(key, value))

'''полученная статистика говорит о том, что в книге преобладает описание(а точнее, называние)
предметов и явлений вокруг, их  большое количество'''

