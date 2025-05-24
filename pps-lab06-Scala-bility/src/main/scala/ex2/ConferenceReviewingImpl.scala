package ex2

import ConferenceReviewing.Question;

trait ConferenceReviewingTrait extends ConferenceReviewing:
  def loadReview(article: Int, scores: Map[ConferenceReviewing.Question, Integer]): Unit

  def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit

  def orderedScores(article: Int, question: ConferenceReviewing.Question): List[Integer]

  def averageFinalScore(article: Int): Double

  def acceptedArticles: Set[Integer]

  def sortedAcceptedArticles: List[(Integer, Double)]

  def averageWeightedFinalScoreMap: Map[Integer, Double]


class ConferenceReviewingImpl extends ConferenceReviewingTrait:
  private var reviews = Seq.empty[(Int, Map[Question, Int])]

  override def loadReview(article: Int, scores: Map[Question, Int]): Unit =
    if (scores.size < Question.values.length)
      throw new IllegalArgumentException
    reviews = (article, scores) +: reviews

  override def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit =
    val scores = Map(
      Question.RELEVANCE -> relevance,
      Question.SIGNIFICANCE -> significance,
      Question.CONFIDENCE -> confidence,
      Question.FINAL -> fin)
    reviews = (article, scores) +: reviews

  override def orderedScores(article: Int, question: Question): List[Integer] =
    reviews
      .filter(_._1 == article)
      .flatMap(_._2.get(question))
      .map(Int.box)
      .sorted
      .toList

  override def averageFinalScore(article: Int): Double =
    val scores = reviews
      .filter(_._1 == article)
      .flatMap(_._2.get(Question.FINAL))
    if (scores.isEmpty) 0.0
    else scores.sum.toDouble / scores.size

  private def accepted(article: Int): Boolean =
    averageFinalScore(article) > 5.0 &&
      reviews
        .filter(_._1 == article)
        .flatMap(_._2.get(Question.RELEVANCE))
        .exists(_ >= 8)

  override def acceptedArticles: Set[Integer] =
    reviews
      .map(_._1)
      .distinct
      .filter(accepted)
      .map(Int.box)
      .toSet

  override def sortedAcceptedArticles: List[(Integer, Double)] =
    acceptedArticles
      .toList
      .map(a => (a, averageFinalScore(a)))
      .sortBy(_._2)

  private def averageWeightedFinalScore(article: Int): Double =
    val scores = reviews
      .filter(_._1 == article)
      .map { case (_, m) => m(Question.FINAL) * m(Question.CONFIDENCE) / 10.0 }
    if scores.isEmpty then 0.0 else scores.sum / scores.size

  override def averageWeightedFinalScoreMap: Map[Integer, Double] =
    reviews
      .map(_._1)
      .distinct
      .map(a => Int.box(a) -> averageWeightedFinalScore(a))
      .toMap