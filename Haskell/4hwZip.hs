-- zip, zipWith и unzip
-- Используйте функции zip, zipWith, unzip.

-- 1 Реализуйте функцию zipWithIndex: 
-- к каждому элементу списка “припишите” его индекс.
-- Т.е. каждый элемент списка должен превратиться в тьюпл, 
-- где вначале идет индекс элемента, а потом сам элемент. 
-- Начинайте считать элементы с нуля.
zipWithIndex :: [a] -> [(Int, a)]
zipWithIndex l = zip [0..] l

-- 2 Сложите каждый элемент списка [Int] с его индексом.
zip2 :: [Int] -> [Int]
zip2 l = foldr (\x b -> ((fst x) + (snd x)) : b) [] (zipWithIndex l) 

-- 3 Дан список [Int], верните список, в котором все четные по счету элементы 
-- заменены на ноль
zip3my :: [Int] -> [Int]
zip3my l = map (\x -> if (fst x) `mod` 2 == 0 then 0 else snd x) (zipWithIndex l)  
-- 4 Дан список, удалите из него все элементы с четным индексом.
zip4 :: [Int] -> [Int]
--zip4 l = snd $ unzip $ filter (\x -> (fst x) `mod` 2 /= 0) (zipWithIndex l)
zip4 = snd . unzip . filter (\x -> (fst x) `mod` 2 /= 0) . zipWithIndex
-- 5 Дан список двузначных чисел. Верните тьюпл из двух списков: 
-- первые цифры чисел и вторые цифры чисел. Например, [10, 44, 31] 
-- превращается в ([1, 4, 3], [0, 4, 1]).
zip5 :: [Int] -> ([Int],[Int])
-- zip5 l = unzip (map (\x -> (div x 10, mod x 10)) l)
-- zip5 l = unzip $ map (\x -> (div x 10, mod x 10)) l
zip5 = unzip . map (\x -> (div x 10, mod x 10))
