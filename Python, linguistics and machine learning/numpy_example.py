#!/usr/bin/python

import numpy

# Create matrix from string

m1 = numpy.matrix('1 2; 3 4')
print(m1)

# Create matrix from list of lists

m2 = numpy.matrix([[1, 2], [3, 4]])
print(m2)

# Create array from list (or list of lists or tuples)

v1 = numpy.array([1, 2, 3, 4])
print(v1)
v2 = numpy.array([[1, 2], [3, 4]])
print(v2)
v3 = numpy.array([[1,2.0],[0,0],(1+1j,3.)])
print(v3)

# Create array (or matrix) of zeros, ones
z1 = numpy.zeros(5)
print(z1)
z2 = numpy.zeros((2, 3))
print(z2)
m = numpy.ones((4, 2))
print(m)


