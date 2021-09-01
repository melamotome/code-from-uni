-- 1 Реализуйте функцию safePop :: [a] -> Maybe (a, [a]), 
-- которая по списку возвращает пару (tuple) из его головы и хвоста. 
-- Результат заворачивается в Maybe, чтобы учесть ситуацию, 
-- когда результата нет, т.е. список пуст.
safePop :: [a] -> Maybe (a, [a])
safePop [] = Nothing
safePop (x:l) = Just (x, l)

-- 2 Используя фунцию safePop, реализуйте функцию 
-- safeGet :: Int -> [a] -> Maybe a, она должна возвращать 
-- элемент списка с указанным индексом.
safeGet :: Int -> [a] -> Maybe a
safeGet 0 (x:_) = Just x
safeGet i l = case safePop l of
                  Nothing -> Nothing
                  Just (_, s) -> safeGet (i-1) s
