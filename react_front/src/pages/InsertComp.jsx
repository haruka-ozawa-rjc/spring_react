import { useLocation, useNavigate } from 'react-router-dom';

function InsertComp() {

    // memberを受け取る
    const location = useLocation();
    const member = location.state;

    // ページ遷移関数の生成
    const navigate = useNavigate();

    // メニュー画面に行く関数
    const handleMenu = () => {
        navigate('/');
    }

    return(
        <>
            <div id="wrapper">
                <div className="menu">
                    <a onClick={ handleMenu }>メニュー</a><br />
                </div>
                <div class="menu">
                    <h2>新規登録&nbsp;完了画面</h2>
                    <p>以下のデータが登録されました</p>
			    </div>
                <form>
                    <table className="ct">
                        <tr>
                            <th>社員ID</th>
                            <td>{ member.memberId }</td>
                        </tr>
                        <tr>
                            <th>氏名</th>
                            <td>{ member.memberName }</td>
                        </tr>
                        <tr>
                            <th>年齢</th>
                            <td>{ member.age }</td>
                        </tr>
                        <tr>
                            <th>性別</th>
                            <td>{ member.sexFlg }</td>
                        </tr>
                        <tr>
                            <th>住所</th>
                            <td>{ member.address }</td>
                        </tr>
                        <tr>
                            <th>電話番号</th>
                            <td>{ member.telephone }</td>
                        </tr>
                        <tr>
                            <th>メールアドレス</th>
                            <td>{ member.mail }</td>
                        </tr>
                        <tr>
                            <th>役職</th>
                            <td>{ member.positionName }</td>
                        </tr>
                        <tr>
                            <th>事業所</th>
                            <td>{ member.placeName }</td>
                        </tr>
                    </table>
                </form>
            </div>
        </>
    )
}

export default InsertComp;