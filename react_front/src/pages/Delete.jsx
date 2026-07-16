import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import MenuLinks from "./MenuLinks";;

function Delete() {

    // メンバー、事業所、役職を定義
    const [member, setMember] = useState([]);
    const [places, setPlaces] = useState([]);
    const [positions, setPositions] = useState([]);

    // ページ遷移関数
    const navigate = useNavigate();

    // urlからidを取得する
    const { id } = useParams();

    // 起動後すぐに実行
    useEffect(() => {
        fetch(`http://localhost:8080/members/delete/${id}`)
            .then(response => response.json())
            .then(data => {
                setMember(data.member);
                setPlaces(data.places);
                setPositions(data.positions);
            })
    }, [id]);

    // メニュー画面に行く関数
    const handleList = () => {
        navigate('/list');
    }

    // 削除ボタン押して、情報を確認画面に送り遷移
    const handleDelete = async() => {

        // 選択した事業所、役職を定義
        const selectedPosition = positions.find(
        position => position.id == member.positionId
        );

        const selectedPlace = places.find(
            place => place.id == member.placeId
        );

        // springに情報を送り、サーバー上での登録処理を実行
        const response = await fetch('http://localhost:8080/members/deleteComp', {
            method: 'POST',
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(member)
        });

        // 保存完了したら完了画面に遷移
        if(response.ok) {
            navigate('/deleteComp', {
                state: {
                ...member, 
                positionName: selectedPosition?.positionName,
                placeName: selectedPlace?.placeName
                }
            });
            return;
        }

        // 例外発生したらアラート表示
        const message = await response.text();
        alert(message);
    }

    // 画面表示
    return(
        <>
            <div id="wrapper">
                <MenuLinks />
                <div className="menu">
                    <h2>削除確認画面</h2>
                    <p>以下のデータを削除します&nbsp;よろしいですか</p>
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
                            <td>{ member.sexFlg === 0 ? "男" : "女" }</td>
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
                            <td>{ positions.find(position => position.id == member.positionId) ?.positionName }</td>
                        </tr>
                        <tr>
                            <th>事業所</th>
                            <td>{ places.find(place => place.id == member.placeId) ?.placeName }</td>
                        </tr>
                    </table>
                    <div className="menu">
						<input type="button" value="削除" onClick={ handleDelete }/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" onClick = { handleList }>戻る</button>
					</div>
                </form>
            </div>
        </>
    )
}

export default Delete;