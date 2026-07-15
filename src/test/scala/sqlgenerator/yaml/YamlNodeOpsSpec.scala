package sqlgenerator.yaml

import org.scalatest.funsuite.AnyFunSuite

class YamlNodeOpsSpec extends AnyFunSuite:

  test("using asScalar on a Sequence node"):
    YamlNode.Sequence(Vector()).asScalar match
      case Left(error) => assert(error == "Cannot map a Sequence node to Scalar")
      case Right(_) => fail("error expected")

  test("using asScalar on a Mapping node"):
    YamlNode.Mapping(Map.empty[String, YamlNode]).asScalar match
      case Left(error) => assert(error == "Cannot map a Mapping node to Scalar")
      case Right(_) => fail("error expected")

  test("using asScalar on a Scalar node"):
    YamlNode.Scalar("aScalarValue").asScalar match
      case Right(value) => assert(value == "aScalarValue")
      case Left(_) => fail("unexpected error")
