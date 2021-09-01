-- Лекция: Функторы, Аппликативные функторы, Монады
-- 1 Сделайте деревья из прошлого раздела (хотя бы одно) функтром.
data Tree a = EmptyTree | TreeNode a (Tree a) (Tree a) deriving Show
instance Functor Tree where
    fmap f (TreeNode x1 t1 t2) = (TreeNode (f x1) (fmap f t1) (fmap f t2))
    fmap _ EmptyTree = EmptyTree

data LeafTree a = Leaf a | InnerNode (LeafTree a) (LeafTree a)
instance Functor LeafTree where
    fmap f (Leaf a) = (Leaf (f a))
    fmap f (InnerNode a b) = (InnerNode (fmap f a) (fmap f b))
-- 2 * Придумайте, как можно сделать листовое дерево аппликативным функтором
instance Applicative LeafTree where
    pure a = Leaf a
    -- (<*>) :: LeafTree (a -> b) -> LeafTree a -> LeafTree b
    (Leaf a) <*> (Leaf b) = Leaf (a b)
    (InnerNode x1 y1) <*> (InnerNode x2 y2) = 
-- 3 Даны два значения Maybe Int. Верните их сумму тоже в виде Maybe Int.
-- Используйте <$> и <*>.
task3 :: Maybe Int -> Maybe Int -> Maybe Int
task3 x1 x2 = (+) <$> x1 <*> x2
-- 4 Даны два списка [Int]. Получите новый список, где каждый элемент 
-- первого сложен с каждым элементов второго. Используйте <$> и <*>.
task4 :: [Int] -> [Int] -> [Int]
task4 l1 l2 = (+) <$> l1 <*> l2  -- liftA2 (+) l1 l2, import Data.Applicative