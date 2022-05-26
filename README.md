# trivia-assessment

An API for presenting and validating trivia questions.

## running the API

## using the API
1. [GET] localhost:8080/generate
2. [GET] localhost:8080/questions
3. [POST] localhost:8080/checkanswer
   ``{
   "questionId": <<insert id(int)>>,
   "answer": "<<insert answer(string)>>"
   }``