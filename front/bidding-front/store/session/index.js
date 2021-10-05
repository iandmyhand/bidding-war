export const state = () => {
    return {
        user: null
    }
}

export const mutations = {
    setUserInfo(state, user) {
        state.user = user
    },

    resetUserInfo() {
        state.user = null
    }

}

export const getters = {
    
    getUserInfo(state) {
        return state.user
    },
}

/* export const actions = {

    signIn({ commit }, paramObj) {
        let data = new FormData()
        data.append('authId', paramObj.authId)
        data.append('authPassword', paramObj.authPassword)

        await this.$axios.post("http://localhost:9090/v1/user/signin", data, {
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }).then(res => {
            const resultData = res.data
            console.log("jaeyoung log :", resultData)

            commit("setUserInfo", resultData)
        }).catch(error => {
            console.log(error)
        })
    },

    signOut({ commit }) {
        this.$axios.post("http://localhost:9090/v1/user/signout", user.user_id, {
                headers: { 'Content-Type': 'application/json;charset=UTF-8' }
            }
        ).then(res => {
            console.log(res.data)
            alert("success modify goods")
        }).catch(error => {
            console.log(error)
        })

        commit("resetUserInfo")
    }
} */