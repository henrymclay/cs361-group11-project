import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import com.dispenser.medication.web.api.MedicationApiDelegate;

@Service
public class MedicationApiDelegateImpl implements MedicationApiDelegate {

    private final NativeWebRequest request;

    public MedicationApiDelegateImpl(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }
}
