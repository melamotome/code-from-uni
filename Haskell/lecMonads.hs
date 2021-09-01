-- Использование монады IO a
-- Функция - пользователь вводит два числа, возвращается сумма
main = do inputAndSum

inputAndSum :: IO Int
inputAndSum = do  
    putStrLn ("Введите x")
    x <- getLine
    putStrLn ("Введите y")
    y <- getLine
    putStr ("Ваш ответ: ")
    return (read x + read y)



-- Создание монады
data Log a = Log a [String] deriving Show
instance Functor Log where
    -- fmap :: (a -> b) -> Log a -> Log b
    fmap f (Log a s) = Log (f a) s

instance Applicative Log where
    pure x = Log x []
    -- (<*>) :: Log (a -> b) -> Log a -> Log b
    (Log a s1) <*> (Log b s2) = Log (a b) (s1 ++ s2)

add1 :: Int -> Log Int
add1 x = Log (x + 1) ["I added a one"]

mul2 :: Int -> Log Int
mul2 x = Log (2 * x) ["I multiplied by two", "twas hard"]

instance Monad Log where
     -- (>>=) :: Log a -> (a -> Log b) -> Log b
     (Log x messages) >>= f = let (Log y newMessages) = f x 
                  in Log y (messages ++ newMessages)
     -- Не надо, ибо описали pure выше в аппликативном функторе определения
     -- return :: a -> Log a
     -- return x = Log x []

-- Проверяем, работает ли
eval6 :: Int -> Log Int
eval6 x = do
   a <- mul2 x
   add1 a

