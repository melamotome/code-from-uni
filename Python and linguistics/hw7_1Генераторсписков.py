#!/usr/bin/python
#1 Написать функцию, которая принимает на
#вход список чисел numbers и возвращает новый
#список sqrt_numbers, содержащий квадратные
#корни неотрицательных элементов numbers
#(используя генератор списков).

import math

numbers = [5, -342, 76, -998, 23, 1, 5, 7]

def sqrts(numbers):
    return [math.sqrt(number) for number in numbers if number >= 0]

print(sqrts(numbers))
