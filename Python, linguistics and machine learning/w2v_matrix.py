import numpy

# TODO: import Word2Vec, load model

words = ['a', 'b', 'c', 'd']


m = numpy.array([[1, 0.5, 0, 0], [0, 0, 1, 0], [1, 2, 0, 0]])

vectors = []

for row in m:
    print(row)
    wvec = []
    for i in range(len(words)):
        current_word = words[i]
        current_weight = row[i]

        print(current_word, current_weight)

        if current_weight > 0:
            try:
                wvec.append(model.wv[word])
            except Exception:
                print('word', word, 'not in model')

    try:
        vectors.append(numpy.mean(wvec))
    except Exception:
        print('empty vector')         

X = numpy.array(vectors)


