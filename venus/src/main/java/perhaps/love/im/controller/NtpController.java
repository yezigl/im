package perhaps.love.im.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import perhaps.love.im.dto.JsonResponse;
import perhaps.love.im.dto.NtpDto;

@RestController
public class NtpController {

    @RequestMapping(value = "/api/v1/ntp")
    public JsonResponse<NtpDto> check() {
        NtpDto ntpDto = new NtpDto();
        ntpDto.setTime(System.currentTimeMillis());
        return JsonResponse.success(ntpDto);
    }
}
