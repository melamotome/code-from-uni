#запись
"""names = ['Иванов', 'Петров']
marks = [4, 5]

with open('t.csv', 'w') as fout:
    for name, mark  in zip(names, marks):
        fout.write('"{0:s}","{1:d}"\n'.format(name, mark))

print(list(zip(names,marks)))"""

#Чтение
names = []
marks = []

with open('t.csv', 'r') as fin:
          for line in fin:
              name, mark = line.strip().solit(',')
              names.append(name.strip('"'))
              marks.append(int(mark.strip('"')))
print('names:', names)
print('marks:', marks)

