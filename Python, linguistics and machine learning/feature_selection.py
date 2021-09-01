#!/usr/bin/python

import numpy as np
import pandas as pd
from sklearn.feature_selection import SelectKBest, f_classif

X = np.array([[0, 1, 2, -1], [0, 0, 2, 1], [0, 1, 1, 0], [0, 2, 2, 2]])
y = [0, 0, 1, 1]

X_pd = pd.DataFrame(X)
y_pd = pd.Series(y)

print(X_pd)

select_k_best_classifier = SelectKBest(score_func=f_classif, k=2)
X_pd_new = select_k_best_classifier.fit_transform(X_pd, y_pd)
mask = select_k_best_classifier.get_support(indices=True)
print(mask)

X_pd_new = X_pd[mask]
print(X_pd_new)
