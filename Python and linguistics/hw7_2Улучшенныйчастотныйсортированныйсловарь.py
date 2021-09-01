#!/usr/bin/python
#2 См. задачу про частотный словарь. Написать
#функцию, подсчитывающую частоты слов в первом
#файле и записывающую их во второй файл.

#Все слова приводятся к нижнему регистру,
#слова длиной менее 3 символов не учитываются.
#Пары слово-частота записываются в файл отсортированными по убыванию частот.
#def freq(filename_in, filename_out):
#…

import string

def freq(filename_in, filename_out):
    with open(filename_in, 'r', encoding='utf8') as fin:
        lines = fin.readlines()
    d={}


    for line in lines:
        line = line.strip(string.whitespace)
        line = line.lower()
        
        for word in line.split(' '):
            if len(word) > 2:
                cleanWord = word
                for chara in word:
                    if chara in string.punctuation:
                        cleanWord = word.replace(chara,"")
                if not word:
                    continue
                d[cleanWord] = d.get(cleanWord, 0) + 1

    
    with open(filename_out, 'w', encoding='utf8') as fout:
        for key, value in sorted(d.items(), key=lambda x: x[1], reverse=True):
            fout.write('{0} {1}\n'.format(key, value))
      
    
freq('textchastoty.txt', 'textchasotyresult.txt')
