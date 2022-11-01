package screen

import LINE_DIVIDER
import data.CartItems
import data.Product
import extensions.getNotEmptyInt
import extensions.getNotEmptyString

class ShoppingProductList(private val selectedCategory: String) : Screen(){

    private val products = arrayOf(
        Product("패션", "겨울 패딩"),
        Product("패션", "겨울 바지"),
        Product("전자기기", "핸드폰"),
        Product("전자기기", "블루투스 이어폰"),
        Product("전자기기", "노트북"),
        Product("반려동물용품", "건식사료"),
        Product("반려동물용품", "습식사료"),
        Product("반려동물용품", "치약"),
        Product("반려동물용품", "간식")
    )

    //배열에서 제공하는 groupBy 연산 사용해서, categoryLabel을 기준으로 해당 상품 쉽게 조회
    private val categories: Map<String, List<Product>> = products.groupBy { product ->
        product.categoryLabel
    }

    fun showProducts(){
        ScreenStack.push(this)
        val categoryProducts = categories[selectedCategory]
        //상품 목록이 하나라도 존재하면 print
        if (!categoryProducts.isNullOrEmpty()){
            println("""
                $LINE_DIVIDER
                선택하신 [$selectedCategory] 카테고리 상품입니다.
            """.trimIndent())

//            val productSize = categoryProducts.size
//            for(index in 0 until productSize){
//                println("$index. ${categoryProducts[index].name}")
//            }
            //위의 코드를 고차함수로 나타낸 것
            categoryProducts.forEachIndexed{index, product ->
                println("$index. ${product.name}")
            }

            showCartOption(categoryProducts)
        } else{
            // 등록된 목록이 하나도 없을 경우
            showEmptyProductMessage(selectedCategory)
        }
    }

    private fun showCartOption(categoryProducts: List<Product>){
        println(
            """
            $LINE_DIVIDER
            장바구니에 담을 상품 번호를 선택해주세요.
            """.trimIndent()
        )

        //번호로 받을 것이기 때문에 Int로 변환, Int로 변환할 수 없는 값을 입력받았으면 Null 반환
        // !! 사용해서 selectedIndex가 Nullable이 아니게 선언하기는 했으나, 추후 실습에서 개선할 예정
//        val selectedIndex = readLine()?.toIntOrNull()!!
        val selectedIndex = readLine().getNotEmptyInt()
        categoryProducts.getOrNull(selectedIndex)?.let{ product ->
            CartItems.addProduct(product)
            println("=> 장바구니로 이동하시려면 #을, 계속 쇼핑하시려면 +을 입력해주세요.")
            val answer = readLine().getNotEmptyString()
            if(answer == "#"){
                val shoppingCart = ShoppingCart()
                shoppingCart.showCartItems()
            } else if(answer == "+"){
                showProducts()
            } else{
                //TODO 그 외 값을 입력한 경우에 대한 처리

            }
        } ?: kotlin.run {
            println("$selectedIndex 번은 목록에 없는 상품 번호 입니다. 다시 입력해주세요.")
            showProducts()
        }
    }

    private fun showEmptyProductMessage(selectedCategory: String) {
        println("[$selectedCategory] 카테고리의 상품이 등록되기 전입니다.")
    }
}