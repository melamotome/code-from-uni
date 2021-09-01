#!/usr/bin/python

import numpy as np
import pandas as pd

X = np.array([[1, 2, 3], [4, 5, 6]])
print('Numpy X:')
print(X)

X_df = pd.DataFrame(X)
print('DataFrame X:')
print(X_df)

filename = 'X.csv'
#looks like ,0,1,2
#           0,1,2,3
#           1,4,5,6
X_df.to_csv(filename)

X_df = pd.read_csv(filename, index_col=0)
print('DataFrame X loaded:')
print(X_df)

X_df.set_axis(['r1', 'r2'], axis=0, inplace=True) #0 - строк, 1 - столбцов.
X_df.set_axis(['a', 'b', 'c'], axis=1, inplace=True) #Тру - в этой структуре сразу же меняет название, ничего не возвращает
print('DataFrame X with new row and column names:')
print(X_df)

y = np.array([7, 8, 9])
print('Numpy array y:')
print(y)

y_df = pd.Series(y)
print('Series y:')
print(y_df)


#У нас было в коде местр
#X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.4, random_state=0)
#вместо X and y подать DataFrame and series
