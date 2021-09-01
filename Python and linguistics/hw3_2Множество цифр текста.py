#!/usr/bin/python

#2 В переменной T хранится текст. Найти множество
#встречающихся в тексте цифр.


t = "It's a 42 text6 bep bep 9990"

numbers = set()

for ch in range(0, len(t)):
      if t.find(str(ch)) != -1:
            numbers.add(ch)
print(numbers)

