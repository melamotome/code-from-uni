import string
import numpy
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error
from sklearn.feature_extraction.text import TfidfVectorizer

def matrix(nameOfFile): #надо возвращать матрицу
    with open(nameOfFile, 'r', encoding='utf8') as fin:
        lines = fin.readlines()

    words = []
    textLines = []
    nLinesPassed = 0
    for line in lines:
        #в этом кусочке я ограничиваю колво строк, которые я вот обрабатываю
        if nLinesPassed > 9:
            break
        nLinesPassed += 1

        line = line.strip(string.punctuation + string.whitespace)
        line = line.split('\t')[0]
        textLine = []
        for word in line.split(' '):
            for chara in word:
                if chara in string.punctuation:
                    word = word.replace(chara,"")
            if not word:
                continue
            textLine.append(word)
            words.append(word) #words это список всех-всех вообще слов
        textLines.append(textLine) #Это вот список строк без пунктуации
        words = sorted(set(words))

    numbersTotal = []
    for line in textLines:
        numbers = [0] * len(words) #делаю пустой список, где сопоставлено число каждому существующему слову
        for i in range(0, len(line)):
            for j in range(0, len(words)):
                if line[i] == words[j]:
                    numbers[j] += 1
        numbersTotal.append(numbers) #список списков(размером с количество слов вообще, которые отсортированиы там были) чисел, где каждой строке соответствует список, где числа стоят в соответствии с тем, как слова появляются в той штуке
    return numpy.matrix(numbersTotal)

def matrix_scikit(nameOfFile, nsOfRows, vectorizerType):
    with open(nameOfFile, 'r', encoding='utf8') as fin:
        corpus = fin.readlines()[:nsOfRows]
    score = []
    for line in corpus:
        line, s = line.rsplit('\t', maxsplit=1)
        score.append(int(s))
    if vectorizerType == "tf-idf":
        vectorizer = TfidfVectorizer()
    elif vectorizerType == "regular":
        vectorizer = CountVectorizer()

    return (vectorizer.fit_transform(corpus).toarray(), numpy.array(score)) #return X and y


def LinearRegression_Predict(X, y):
    regr = LinearRegression()
    #Разбиваем на тестовые и тренинговые
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.4, random_state = 0)
    regr.fit(X_train, y_train)  # Train linear regression.
    y_pred = regr.predict(X_test)  # Make predictions.
    return mean_squared_error(y_test, y_pred)

def makeLog(nameOfFile, nameOfLog, nsOfRows):
    sizes = []
    vectorizerTypes = []
    errors = []
    uniqueWords = []
    for n in nsOfRows:
        x, y = matrix_scikit(nameOfFile, n, "regular")  # tf-idf or regular
        sizes.append(n)
        vectorizerTypes.append("regular")
        errors.append(LinearRegression_Predict(x, y))
        uniqueWords.append(x.shape[1])

        x, y = matrix_scikit(nameOfFile, n, "tf-idf")  # tf-idf or regular
        sizes.append(n)
        vectorizerTypes.append("tf-idf")
        errors.append(LinearRegression_Predict(x, y))
        uniqueWords.append(x.shape[1])

    with open(nameOfLog, 'w') as fout:
        for i in range(0, len(sizes)):
            fout.write('{0}\t{1}\t{2}\t{3}\n'.format(sizes[i], vectorizerTypes[i], errors[i], uniqueWords[i]))


# x, y = matrix_scikit('linis_data.txt', 50, regular)#tf-idf or regular
# print(LinearRegression_Predict(x, y))

nsOfRows = [50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000]
makeLog('linis_data.txt', 'linis_data_log.csv', nsOfRows)
