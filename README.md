Увидел только 1 страницу задание по этому все делел по своему виденью,
в принципе что, все подходит по заднию, кроме эмитации запросов(corutines+flow)
на точку API(просто не видел)

------------ПРОБЛЕМЫ С КОТОРЫМИ СТОЛКНУЛСЯ(БАГИ) -----------------------------

SLPASH SCREEN - работает какое то время,
  то есть примерно 2-3 дня он у меня был, потом пропал(не анимация), переустановил приложение,
  опять появился, вероятно опять пропадёт

  На телефонах API >31 SplashScreen вообще не будет, ни стандратного никакого,
  я его убрал, будет только анимация

   Вообще думал что именно анимация должна быть на стандартном splashScreen, но как я понял, она делаеться
   там не при помощи Lottie, а при помощи верстки в XML

   Анимацию сделал в отдельной Activity, после нее будет переход на уже рабочую

СПИСОК КАТЕГОРИЙ
    Список категори реализовал, так что есть только категории, в которых есть продукты,
    то есть, если этой категории нет продуктов, то и этой категории не будет, как по мне логично,
    потому что если их оставить, постоянно крашилось приложение из-за перехода на несуществующий элемент
    в LazyGrid, конечно можно было просто в таком случает просто отключить переход, но как по мне станно
    что есть категория, где нет продукта

ПЕРВЫЙ ЭКРАН
    Не смог разобраться с тем, что при заходе на первый экран он загружаеться, потом полностью уничтожаеться
    а после этого опять заходит, то есть 2 раза(погуглил, думаю что из-за Hilt, но это не точно :) ),
    так больше проблем нет


ПРОСЬБА
    Дайте ответ, на софт, хотя бы 1 словом)))
    tg : @svodov