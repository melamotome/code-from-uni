#!/usr/bin/python

#2 Завести модуль, содержащий базовые
#лингвистические функции (см. пример
#частотного словаря) и проверить работу
#модуля из отдельного скрипта

def oftendict(s):
    d = {}
    for word in s.split(' '):
        if not word:
            continue
        d[word] = d.get(word, 0) + 1
    return d

def howmany(s,ch):
    summ = 0
    for i in range (0, len(s)):
        if s[i] == ch:
            summ = summ + 1
    return summ
