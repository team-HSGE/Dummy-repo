package data

//프로젝트 전역에서 상품 데이터를 저장하는 클래스
//항상 같은 값이 유지되어야 하기 때문에 싱글턴 객체로 만드는 것이 적합
object CartItems {

    private val mutableProducts = mutableMapOf<Product, Int>() //외부에서는 수정X
    val products: Map<Product, Int> = mutableProducts // read-only 만 가능

    fun addProduct(product: Product){
        //Map에서 키를 조회했을 때, 키가 없을 수도 있음 -> nullable
        mutableProducts[product]?.let{
            mutableProducts[product] = it + 1
        }?: kotlin.run {
            //처음 product를 추가할때 수량 정보를 1로 할당
            mutableProducts[product] = 1
        }
    }


}