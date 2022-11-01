package extensions

//File로 추가한 이유? -> Top-Level의 함수로 확장 함수를 추가하려고 해서
fun String?.getNotEmptyString(): String {
    var input = this
    while(input.isNullOrBlank()){
        println("값을 입력해주세요")
        input = readLine()
    }
    return input.trim()  //공백을 포함한 부분을 제거한 뒤 return
}

//사용자에게 상품 번호입력받을 때 Int형으로 변환할 수 있는지 확인할 때 사용
fun String?.getNotEmptyInt(): Int{
    var input = this?.trim()
    while(input.isNullOrEmpty() || input.toIntOrNull() == null){
        println("값을 입력해주세요")
        input = readLine()
    }
    return input.toInt()
}