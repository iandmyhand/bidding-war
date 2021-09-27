package com.study.biddingwar.common.util

import com.study.biddingwar.domain.dto.SessionDto
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest


/**
 * 현재 JSession 기준으로 작성되어 있음
 * @TODO --> Redis 구성에 맞추어 수정 필요
 */
class SessionUtil {
    companion object {
        const val SESSION_DATA_KEY = "defaultSession"

        fun getSessionDto(): Any? {
            return getData(SESSION_DATA_KEY)
        }

        fun setSessionDto(sessionDto: SessionDto) {
            setData(SESSION_DATA_KEY, sessionDto)
        }

        fun getData(key: String): Any? {
            return this.getRequest().session.getAttribute(key)
        }

        fun setData(key: String?, data: Any?) {

            if (!key.isNullOrBlank() && data != null) {
                this.getRequest().session.setAttribute(key, data)
            }
        }

        fun removeData(key: String) {
            this.getRequest().session.removeAttribute(key)
        }

        fun removeSession() {
            this.getRequest().session.removeAttribute(SESSION_DATA_KEY)
            this.getRequest().session.invalidate()
        }

        fun getRequest(): HttpServletRequest {
            val attributes = RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
            return attributes.request
        }
    }

}