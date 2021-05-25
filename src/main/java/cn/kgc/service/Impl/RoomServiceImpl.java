package cn.kgc.service.Impl;

import cn.kgc.pojo.Rooms;
import cn.kgc.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class RoomServiceImpl extends BaseServiceImpl<Rooms> implements RoomService {
}
