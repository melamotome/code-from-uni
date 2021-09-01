#!/usr/bin/python

#1 Написать функцию, инвертирующую словарь

a = dict({1: 'a', 2: 'b'})

#b = dict({‘a’: 1, ‘b’: 2})

b = dict()

for key, value in a.items():
      b[value] = key
      
print(b)


