
function CustomButton() {
    let isLoggedIn = true;  // 로그인했음
    // let content;

    // if (isLoggedIn) {
    //     content = <button>Log Out</button>;
    // } else{
    //     content = <button>Log In</button>;
    // }

    return(
        <>
            {
                isLoggedIn ? (
                    <button>Log Out</button>
                ) : (
                    <button>Log In</button>
                )
            }
        </>

    );
  }

export default CustomButton;    // 외부에서 사용하려면 필수
