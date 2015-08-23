import com.lmax.tool.disruptor.DisruptorProxy;

/**
 * Created by sirin_000 on 21/08/2015.
 */
@DisruptorProxy
public interface Book extends BookReader, BookWriter{
}
