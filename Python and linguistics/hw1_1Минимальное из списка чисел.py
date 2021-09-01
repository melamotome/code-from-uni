#!/usr/bin/python

#Минимальное число из списка numbers = [3, 5, 1, 9, 0, 4, 2]

numbers = [3, 5, 1, 9, 0, 4, 2]
def minim(numb):
    minim = numb.pop(0)
    for i in numb:
        if i < minim:
            minim = i
    return minim
print(minim(numbers))

 
