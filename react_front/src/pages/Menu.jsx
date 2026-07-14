import { Link } from "react-router-dom";

function Menu() {
    
    // メニューの表示
    return (
        <>
            <h2>メニュー</h2>
            <Link to="/list">メンバー一覧へ移動</Link><br />
            <Link to="/insert">新規登録へ移動</Link>
        </>
    )
}

export default Menu;