#!/usr/bin/python

'''Написать функцию, преобразующую данные из
файла формата CSV в HTML-таблицу
<table>             <!-- Начало таблицы -->
<tr>                <!-- Начало строки -->
<td>Ячейка</td>     <!-- Ячейка 1:1 -->
<td>Ячейка</td>     <!-- Ячейка 1:2 -->
</tr>               <!-- Конец строки -->
<tr>                <!-- Начало строки -->
...
</tr>               <!-- Конец строки -->
</table>            <!-- Конец таблицы -->
'''

import string


things = []

with open('t.csv', 'r', encoding='utf8') as fin:
    i = 0
    for line in fin:
        line = line.replace('\"', '')
        things.append([])
        for element in line.split(','):
            print(element)
            things[i].append(element.replace("\n",''))
        i += 1

print(things)

with open('t.html', 'w', encoding='utf8') as fout:
    fout.write("<table>\n")
    for i in range(0, len(things)):
        fout.write("<tr>\n")
        for j in range(0, len(things[i])):
            fout.write("<td>{0}</td>\n".format(things[i][j]))
        fout.write("</tr>\n")
    fout.write("</table>\n")



