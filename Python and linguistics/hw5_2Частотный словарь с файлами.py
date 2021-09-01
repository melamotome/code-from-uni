#!/usr/bin/python

#2 Написать функцию, подсчитывающую частоты слов в
#первом файле и записывающую их во второй файл
#(необходимо использовать пример с частотным
#словарем из лекции №3)

import string

def freq(filename_in, filename_out):
    with open(filename_in, 'r', encoding='utf8') as fin:
        lines = fin.readlines()
    d={}

    for line in lines:
        line = line.strip('\n'+'\r'+'\n'+'\\'+'\''+'\"') #line = line.strip(string.punctuation + string.whitespace)
        for word in line.split(' '):
            for chara in word:
                if chara in string.punctuation:
                    word = word.replace(chara,"")
            if not word:
                continue
            d[word] = d.get(word, 0) + 1
    with open(filename_out, 'w', encoding='utf8') as fout:
        for key, value in d.items():
            fout.write('{0} {1}\n'.format(key, value))
      
    
freq('textchastoty.txt', 'textchasotyresult.txt')
