import { useLocation , useNavigate } from 'react-router-dom';
import MenuLinks from "./MenuLinks";

function UpdateConf() {

    // 更新画面からフォーム情報を受け取る
    const location = useLocation();
    const { member, places, positions } = location.state;

    // ページ遷移関数
    const navigate = useNavigate();

    // 戻る押下で更新画面へ遷移
    const handleBack = () => {
        navigate(`/update/${ member.memberId }`, {
            state: {
                member,
                places, 
                positions,
            },
        });
    }

    // 登録処理
    const handleUpdate = async() => {

        console.log(member);

        // springに情報を送り、サーバー上での登録処理を実行
        const response = await fetch('http://localhost:8080/members/updateComp', {
            method: 'POST',
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(member)
        });

        // 保存完了したら完了画面に遷移
        if(response.ok) {
            navigate('/updateComp', {
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
                <MenuLinks />
                <div className="menu">
                    <h2>更新確認画面</h2>
                    <p>以下のデータに更新を行います&nbsp;よろしいですか</p>
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
                            <td>{ member.sexFlg === "0" ? "男" : "女" }</td>
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
                        <input type="button" value="更新" onClick={ handleUpdate } />
                        &nbsp;&nbsp;&nbsp;&nbsp;
					    <button type="button" value="戻る" onClick = { handleBack }>戻る</button>
                    </div>
                </form>
            </div>
        </>
    )
}

export default UpdateConf;