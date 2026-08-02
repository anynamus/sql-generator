package alchemy.core

import alchemy.core.Result

trait Decoder[-I, +A]:
  def decode(input: I): Result[A]