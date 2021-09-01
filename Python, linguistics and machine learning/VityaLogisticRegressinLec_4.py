# Микро- и макроусреднение - Confusion Matrix n*n или 2*2, взвешенное усреднени(сумма коэффициентов единица)
# Ищем параметры, а потом считаем ошибочные меры(последний слайд 5 лекции)
# C = 10000
# Менять Window и Size в модели, проверить разные scores (они --> 1)
from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import classification_report
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score, roc_auc_score
from lec_3_1 import make_matrix_tfidf_with_model


def reduce_classes(filename):
    with open(filename, 'r', encoding='utf8') as inf, open('logistic_'+filename, 'w', encoding='utf8') as ouf:
        for line in inf:
            text, tone = line.rsplit('\t', maxsplit=1)
            if not tone or not text:
                continue
            if '-2' in tone:
                tone = '-1\n'
            elif '2' in tone:
                tone = '1\n'
            ouf.write('{0}\t{1}'.format(text, tone))

if __name__ == '__main__':
    # reduce_classes('clean_lin_mystem.txt')

    X, y = make_matrix_tfidf_with_model('logistic_clean_lin_mystem.txt')

    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.4, random_state=0)
    '''
    tuned_parameters = [{'C': [10000]}]
    scores = ['f1']

    for score in scores:

        clf = GridSearchCV(LogisticRegression(), tuned_parameters, cv=5, scoring='%s_macro' % score)
        clf.fit(X_train, y_train)
        print('Best parameters set found on development set:\n', clf.best_params_)
        y_true, y_pred = y_test, clf.predict(X_test)
        print('Detailed classification report:\n', classification_report(y_true, y_pred))
    '''
    regr = LogisticRegression(C=10000)
    regr.fit(X_train, y_train)

    y_pred = regr.predict(X_test)
    y_score = regr.predict_proba(X_test)

    print(accuracy_score(y_test, y_pred))
    print(precision_score(y_test, y_pred, average='micro'))
    print(recall_score(y_test, y_pred, average='micro'))
    print(f1_score(y_test, y_pred, average='micro'))
    # print(roc_auc_score(y_test, y_score, average='micro')) - OneVsRestClassifier
