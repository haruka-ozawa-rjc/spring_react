import { useNavigate } from 'react-router-dom';

function MenuLinks() {

    // ページ遷移関数の生成
    const navigate = useNavigate();

    // メニュー画面に行く関数
    const handleMenu = () => {
        navigate('/');
    }

    // メニュー画面に行く関数
    const handleList = () => {
        navigate('/list');
    }

    return(
        <>
            <div id="wrapper">
                <div className="menu">
                    <a onClick={ handleMenu }>メニュー</a><br />
                    <a onClick={ handleList }>メンバ一覧画面</a><br />
                </div>
            </div>
        </>
        )
}

export default MenuLinks;