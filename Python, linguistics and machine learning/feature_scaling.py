#!/usr/bin/python

import numpy as np
from sklearn import preprocessing
import pandas as pd

# Feature scaling
# http://scikit-learn.org/stable/modules/preprocessing.html
# http://scikit-learn.org/stable/auto_examples/preprocessing/plot_all_scaling.html

X_train = np.array([[ 10, -1,  2],
                    [ 20,  0,  0],
                    [ 0,  1, -1]])

print(X_train)

# Srandard scaling: mean: 0, standard deviation: 1
X_scaled = preprocessing.scale(X_train) 
print(X_scaled)

scaler = preprocessing.StandardScaler()
X_scaled = scaler.fit_transform(X_train)
print(X_scaled)

# MinMaxScaler: scale to [0, 1]
min_max_scaler = preprocessing.MinMaxScaler()
X_scaled = min_max_scaler.fit_transform(X_train)
#X_scaled = preprocessing.minmax_scale(X_train)
print(X_scaled)

# MaxAbsScaler: scale to [-1, 1]
max_abs_scaler = preprocessing.MaxAbsScaler()
X_scaled = max_abs_scaler.fit_transform(X_train)
#X_scaled = preprocessing.maxabs_scale(X_train)
print(X_scaled)

X_train = np.array([[ 100, -1,  2],
                    [ 2,  0,  0],
                    [ 0,  1, -1],
                    [1, 1, 0],
                    [0, 1, 2]])


# Robust scaling (robust to outliers)
X_scaled = preprocessing.robust_scale(X_train)
print(X_scaled)

#X_scaled = preprocessing.minmax_scale(X_train)
#print(X_scaled)

# Removing outliers

df = pd.DataFrame(X_scaled)
df_clean = df[df.apply(lambda x: np.abs(x - x.mean()) / x.std() < 1.7).all(axis=1)]

#print(df.apply(lambda x: np.abs(x - x.mean()) / x.std() < 1.7))
#print(df[[False, True, False, True, True]])
print(df_clean)


