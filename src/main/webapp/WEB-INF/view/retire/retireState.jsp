<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>퇴사자 퇴직처리 취소</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
        }
        table {
            width: 100%;
            margin-top: 20px;
        }
        td {
            padding: 10px;
        }
        select, input[type="date"], input[type="text"] {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 4px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
	    /* 저장 버튼 스타일 */
	    input[type="button"] {
	        background-color: #4CAF50;
	        color: white;
	        border: none;
	        padding: 12px 25px;
	        cursor: pointer;
	        border-radius: 25px;
	        font-size: 16px;
	        font-weight: bold;
	        transition: background-color 0.3s, transform 0.2s;
	        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
	    }
	
	    input[type="button"]:hover {
	        background-color: #45a049;
	        transform: scale(1.05); /* 버튼을 살짝 확대 */
	    }
	
	    input[type="button"]:active {
	        background-color: #3e8e41;
	        transform: scale(0.98); /* 클릭 시 살짝 축소 */
	    }
    </style>
</head>
<body>
    <h2>퇴사자 퇴직처리 취소</h2>
    <form action="/retire/retireState.do" method="POST" id="retireStateForm">
        <input type="hidden" name="empNo" id="empNo" value="${param.empNo}"/>
        <table>
            <tr>
                <td>퇴직구분</td>
                <td>
                    <select id="retiredForm" name="retiredForm">
                        <option value="retirementage">정년퇴직</option>
                        <option value="redundancy">정리해고</option>
                        <option value="voluntaryretire">자발적 퇴직</option>
                        <option value="retirementofexecutives">임원 퇴작</option>
                        <option value="interimsettlement">중간 정산</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>퇴직일자</td>
                <td><input type="date" id="retiredDate" name="retiredDate"></td>
            </tr>
            <tr>
                <td>퇴직 후 연락처</td>
                <td><input type="text" id="retiredPhonenum" name="retiredPhonenum" placeholder="예: 010-1234-5678"></td>
            </tr>
        </table>
        <input type="button" onclick="javascript:fnSaveRetireState();"value="저장">
    </form>
    <script type="text/javascript">
        //퇴직처리정보 저장
        function fnSaveRetireState(){

            // 각 입력 필드의 값 가져오기
            const retiredForm = document.getElementById("retiredForm").value;
            const retiredDate = document.getElementById("retiredDate").value;
            const retiredPhonenum = document.getElementById("retiredPhonenum").value;

            // 항목 null 값 체크
            if (!retiredForm || !retiredDate || !retiredPhonenum) {
                alert("퇴사 정보를 모두 기입해주세요.");
                return;
            }
            
            //저장 실행
            if(confirm("정말로 퇴직처리를 하시겠습니까?")){
                document.getElementById("retireStateForm").submit();
            }
        }
    </script>
</body>
</html>