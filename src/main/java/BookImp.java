import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.tool.disruptor.*;
import javassist.util.proxy.ProxyFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by sirin_000 on 21/08/2015.
 */
public final class BookImp implements Book {
    private static final Book bookImp = new BookImp();

    private static final int pendingRequests = 2;
    private static final int numThreads = Runtime.getRuntime().availableProcessors();
    private static final int bufferLen = numThreads * pendingRequests;

    private static final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    private static final RingBufferProxyEventFactory proxyEventFactory = new RingBufferProxyEventFactory();
    private static final Disruptor<ProxyMethodInvocation> disruptor = new Disruptor<>(proxyEventFactory, bufferLen, singleThreadExecutor);
    private static final RingBufferProxyGeneratorFactory generatorFactory = new RingBufferProxyGeneratorFactory();
    private static final RingBufferProxyGenerator generator = generatorFactory.newProxy(GeneratorType.BYTECODE_GENERATION);

    private static final ThreadLocal<Book> writeProxy = new ThreadLocal<Book>() {
        @Override final protected Book initialValue() {
            return generator.createRingBufferProxy(Book.class, disruptor, OverflowStrategy.BLOCK, bookImp);
        }
    };
    private static final ThreadLocal<BookReader> readProxy = new ThreadLocal<BookReader>() {
        @Override final protected BookReader initialValue() {
            return generator.createRingBufferProxy(BookReader.class, disruptor, OverflowStrategy.BLOCK, bookImp);
        }
    };

    static {
        disruptor.start();
    }

    @Override
    public void on() {
    }
}
