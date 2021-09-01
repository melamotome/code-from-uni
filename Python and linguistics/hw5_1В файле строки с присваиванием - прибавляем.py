#!/usr/bin/python

#1 Написать функцию, считывающую из файла строки:
#a=4
#b=6
#увеличивающую значения a и b на 1 и 5
#соответственно, и вывести результат в тот же файл:
#a=5
#b=11
#* см. split() и int()


with open('thw1.txt', 'r', encoding="utf8") as fin:
    lines = fin.readlines()
print(lines)

lines[0] = lines[0].strip('\n')
for i in range(0, len(lines)):
    lines[i] = lines[i].split("=")
lines[0][1] = str(int(lines[0][1]) + 1)
lines[1][1] = str(int(lines[1][1]) + 5)

for i in range(0, len(lines)):
    lines[i] = "=".join(lines[i])

with open('thw1.txt', 'w', encoding="utf8") as fout:
    for i in range(0, len(lines)):
        fout.write('{0}\n'.format(lines[i]))

