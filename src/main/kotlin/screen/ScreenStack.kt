package screen

object ScreenStack {
    private val screenStack = mutableListOf<Screen>()

    fun push(screen:Screen){
        screenStack.add(screen)
    }
    fun pop(){
        screenStack.removeLastOrNull()
    }
    fun peek(): Screen?{
        return screenStack.lastOrNull()
    }
}

//지금까지 추가했던 모든 화면들 -> Screen의 subClass가 될 것이다.
sealed class Screen