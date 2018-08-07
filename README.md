# isarn-sketches-algebird-api
Type-classes to interface [isarn-sketches](https://github.com/isarn/isarn-sketches) with [Algebird](https://twitter.github.io/algebird/)

### API documentation
https://isarn.github.io/isarn-sketches-algebird-api/latest/api/

### How to use in your project

``` scala
libraryDependencies += "org.isarnproject" %% "isarn-sketches-algebird-api" % "0.1.2"
```

This package builds against some `% Provided` dependencies, which you may need to include:

```scala
libraryDependencies += "org.isarnproject" %% "isarn-sketches" % "0.1.2"
libraryDependencies += "com.twitter" %% "algebird-core" % "0.13.4"
```

### Algebird Aggregators with TDigest
``` scala
scala> import org.isarnproject.sketchesAlgebirdAPI.AlgebirdFactory
import org.isarnproject.sketchesAlgebirdAPI.AlgebirdFactory

scala> val data = Vector.fill(10000) { scala.util.Random.nextGaussian() }
data: scala.collection.immutable.Vector[Double] = Vector(-0.17214021856256478, 1.1041922756714304, ...

scala> val agg = AlgebirdFactory.tDigestAggregator[Double]
agg: com.twitter.algebird.MonoidAggregator[Double,org.isarnproject.sketches.TDigest,org.isarnproject.sketches.TDigest] = com.twitter.algebird.Aggregator$$anon$3@47d12bd7

scala> val td = agg(data)
td: org.isarnproject.sketches.TDigest = TDigest(0.5,0,100,TDigestMap(-3.6423168118013396 -> (1.0, 1.0), ...

scala> td.cdf(0)
res0: Double = 0.49907435051894633

scala> td.cdfInverse(0.5)
res1: Double = 0.0022612631398306604
```

### Algebird Monoids with TDigest
``` scala
scala> import org.isarnproject.sketchesAlgebirdAPI._, org.isarnproject.sketches.TDigest
import org.isarnproject.sketchesAlgebirdAPI._
import org.isarnproject.sketches.TDigest

scala> val data = Vector.fill(100) { Vector.fill(10000) { scala.util.Random.nextGaussian() } }
data: scala.collection.immutable.Vector[scala.collection.immutable.Vector[Double]] = Vector(Vector(0.1778102040514962, ...

scala> val tdvec = data.map(TDigest.sketch(_))
tdvec: scala.collection.immutable.Vector[org.isarnproject.sketches.TDigest] = Vector(TDigest(0.5,0,75,TDigestMap(-4.215406387806561 -> (1.0, 1.0), ...

scala> val tdsum = com.twitter.algebird.Monoid.sum(tdvec)
tdsum: org.isarnproject.sketches.TDigest = TDigest(0.5,0,113,TDigestMap(-4.776867999224537 -> (1.0, 1.0), ...

scala> tdsum.cdf(0)
res5: Double = 0.4985982756854377
```
