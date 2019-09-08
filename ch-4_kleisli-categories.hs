{-# LANGUAGE MultiParamTypeClasses #-}
module Foo where

-- Construct the Kleisli category for partial functions.
class Object o where
class Morphism a b m where
  apply :: m a b -> a -> b
class (Object o, Morphism m) => Category o m cat where
  identity :: morphism a a
  compose :: morphism a b -> morphism b c -> morphism a c

data Result a = N | J a

type Object a = a
type Morphism a b = a -> Result b

id :: Result a
id = N

cmps :: Morphism a b -> Morphism b c -> Morphism a c
cmps f g x = case f x of
  N   -> N
  J y -> g y


-- Implement the embellished function safe_reciprocal that returns a valid
-- reciprocal of its argument if it's different from zero.

-- Compose safe_root and safe_reciprocal to implement safe_root_reciprocal that
-- calculates sqrt(1/x) whenever possible.
