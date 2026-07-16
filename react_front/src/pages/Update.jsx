import { useEffect, useState } from 'react';
import { useParams, useNavigate, useLocation } from 'react-router-dom';
import { useForm } from "react-hook-form";
import MenuLinks from "./MenuLinks";

function Update() {

    // 「戻る」から来た情報を保持
    const location = useLocation();
    
    // urlからidを取得する
    const { id } = useParams();

    // ページ遷移関数
    const navigate = useNavigate();

    // バリデーションの関数を定義
    const {
        register,
        reset,
        handleSubmit,
        formState: { errors },
    } = useForm();
    
    // 起動後すぐに実行
    useEffect(() => {

        // 戻るからの情報があれば表示
        if (location.state) {

            setMember(location.state.member);
            setPlaces(location.state.places);
            setPositions(location.state.positions);

            reset(location.state.member);
            return;
        }

        fetch(`http://localhost:8080/members/update/${id}`)
            .then(response => response.json())
            .then(data => {
                setMember(data.member);
                setPlaces(data.places);
                setPositions(data.positions);

                reset(data.member);
            })
    }, [id, reset]);

    // メンバー、事業所、役職を定義
    const [member, setMember] = useState([]);
    const [places, setPlaces] = useState([]);
    const [positions, setPositions] = useState([]);

    // メニュー画面に行く関数
    const handleList = () => {
        navigate('/list');
    }

    // 更新ボタン押して、情報を確認画面に送り遷移
    const onSubmit = (data) => {

        // 選択した事業所、役職を定義
        const selectedPosition = positions.find(
            position => position.id == data.positionId
        );
        const selectedPlace = places.find(
            place => place.id == data.placeId
        );

        // 確認画面に遷移、registDateなども引き継ぐ場合、memberを入れる
        navigate('/updateConf', {
            state: {
                member: {
                    ...member,
                    ...data,
                    positionName: selectedPosition?.positionName,
                    placeName: selectedPlace?.placeName
                },
                places,
                positions,
            },
        },);
    }

    // 画面表示
    return(
        <>
            <div id="wrapper">
                <MenuLinks />
                <div className="menu">
                    <h2>更新画面</h2>
                    <p>以下のデータの更新を行います</p>
                </div>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <table className="ct">
                        <tr>
                            <th>社員ID</th>
                            <td>
                                <input type="text"
                                    {...register("memberId", {
                                       required: "社員IDは必須です。",
                                       maxLength: {
                                        value: 10, 
                                        message: "10文字以内で入力してください。",
                                       },
                                    })}/>
                                {errors.memberId && (
                                    <div className="error-message">{errors.memberId.message}</div>
                                )}
                            </td>
                        </tr>
                        <tr>
                            <th>氏名</th>
                            <td>
                                <input type="text"
                                    {...register("memberName", { 
                                       required: "氏名は必須です。", 
                                       maxLength: {
                                        value: 40,
                                        message: "40文字以内で入力してください。" ,
                                       },
                                    })}/>
                                {errors.memberName && (
                                    <div className="error-message">{errors.memberName.message}</div>
                                )}
                            </td>
                        </tr>
                        <tr>
                            <th>年齢</th>
                            <td>
                                <input type="text"
                                    {...register("age", {
                                        required: "年齢は必須です。",
                                        maxLength: {
                                            value: 3,
                                            message: "3桁以内で入力してください",
                                        },
                                    })}/>
                                {errors.age && (
                                    <div className="error-message">{errors.age.message}</div>
                                )}
                            </td>
                        </tr>
                        <tr>
                            <th>性別</th>
                            <td>
                                男<input 
                                    type="radio" 
                                    value= "0" 
                                    {...register("sexFlg", {
                                        required: "性別は必須です。",
                                    })}/>
                                女<input 
                                    type="radio" 
                                    value= "1"
                                    {...register("sexFlg", {
                                        required: "性別は必須です。",
                                    })}/>
                                {errors.sexFlg && (
                                    <div className="error-message">{errors.sexFlg.message}</div>
                                )}
                            </td>
                        </tr>
                        <tr>
                            <th>住所</th>
                            <td>
                                <input type="text"
                                    {...register("address", {
                                        maxLength: {
                                            value: 50, 
                                            message: "50文字以内で入力してください",
                                    },
                                })}/>
                                {errors.address && (
                                    <div className="error-message">{errors.address.message}</div>
                                )}
                            </td>
                        </tr>
                        <tr>
                            <th>電話番号</th>
                            <td>
                                <input type="text"
                                     {...register("telephone", {
                                        maxLength: {
                                            value: 11,
                                            message: "11文字以内で入力してください"
                                       },
                                    })}/>
                                {errors.telephone && (
                                    <div className="error-message">{errors.telephone.message}</div>
                                )}
                            </td>
                        </tr>
                        <tr>
                            <th>メールアドレス</th>
                            <td>
                                <input type="text"
                                       {...register("mail", {
                                        maxLength: {
                                            value: 20,
                                            message: "20文字以内で入力してください"
                                       },
                                    })}/>
                                {errors.mail && (
                                    <div className="error-message">{errors.mail.message}</div>
                                )}
                            </td>
                        </tr>
                        <tr>
                            <th>役職</th>
                            <td>
                                <select {...register("positionId")} >
                                    <option value="">選択してください</option>
                                    {positions.map((position) => (
                                        <option key={ position.id } value={ position.id }>
                                            { position.positionName }
                                        </option>
                                    ))}
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>事業所</th>
                            <td>
                                <select {...register("placeId")} >
                                    <option value="">選択してください</option>
                                    { places.map((place) => (
                                        <option key={ place.id } value={ place.id }>
                                            { place.placeName }
                                        </option>
                                    ))}
                                </select>
                            </td>
                        </tr>
                    </table>
                    <div className="menu">
						<input type="submit" value="更新"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" onClick = { handleList }>戻る</button>
					</div>
                </form>
            </div>
        </>
    )
}

export default Update;