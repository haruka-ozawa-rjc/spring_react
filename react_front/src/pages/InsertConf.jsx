import { useLocation, useNavigate } from 'react-router-dom';

function InsertConf() {

    // 登録画面からフォーム情報を受け取る
    const location = useLocation();
    const member = location.state;

    // ページ遷移の関数を定義
    const navigate = useNavigate();

    // メニュー遷移
    const handleMenu = () => {
        navigate('/');
    }

    // 登録処理
    const handleInsert = async() => {

        // springに情報を送り、サーバー上での登録処理を実行
        const response = await fetch('http://localhost:8080/members/insertComp', {
            method: 'POST',
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(member)
        });

        // 保存完了したら完了画面に遷移
        if(response.ok) {
            navigate('/insertComp', {
                state: member
            });
            return;
        }

        // 例外発生したらアラート表示
        const message = await response.text();
        alert(message);
    };

    return (
        <>
            <div id="wrapper">
                <div className="menu">
                    <a onClick={ handleMenu }>メニュー</a><br />
                </div>
                <div class="menu">
                    <h2>新規登録&nbsp;確認画面</h2>
                    <p>以下のデータを登録します&nbsp;よろしいですか</p>
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
                    <div className="menu">
                        <input type="button" value="登録" onClick={ handleInsert } />
                    </div>
                </form>
            </div>
        </>
    )
}

export default InsertConf;