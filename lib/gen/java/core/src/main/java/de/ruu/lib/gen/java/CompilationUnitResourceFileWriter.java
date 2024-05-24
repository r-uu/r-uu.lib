package de.ruu.lib.gen.java;

import static de.ruu.lib.util.Files.writeToFile;

import java.io.IOException;
import java.nio.file.Path;

import de.ruu.lib.util.Files;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

public interface CompilationUnitResourceFileWriter
{
	Path DEFAULT_ROOT_DIRECTORY = Path.of(".", "src/gen/resources");

	CompilationUnitResourceFileWriter root          (@NonNull Path   root);
	CompilationUnitResourceFileWriter packageName   (@NonNull String packageName);
	CompilationUnitResourceFileWriter simpleFileName(@NonNull String simpleFileName);
	CompilationUnitResourceFileWriter fileExtension (@NonNull String fileExtension);
	
	void write(@NonNull String output) throws IOException;

	@Slf4j
	@Getter
	@Accessors(fluent = true)
	abstract class CompilationUnitResourceFileWriterAbstract implements CompilationUnitResourceFileWriter
	{
		private Path root = DEFAULT_ROOT_DIRECTORY;

		private String packageName;
		private String simpleFileName;
		private String fileExtension;

		protected CompilationUnitResourceFileWriterAbstract(
				@NonNull String packageName, @NonNull String simpleFileName, @NonNull String fileExtension)
		{
			if (packageName   .isEmpty()) throw new IllegalArgumentException(    "package name may not be empty");
			if (simpleFileName.isEmpty()) throw new IllegalArgumentException("simple file name may not be empty");
			if (fileExtension .isEmpty()) throw new IllegalArgumentException(  "file extension may not be empty");
			
			this.packageName    = packageName;
			this.simpleFileName = simpleFileName;
			this.fileExtension  = fileExtension;
		}

		@Override public CompilationUnitResourceFileWriter root(@NonNull Path root) // TODO validation?
		{
			this.root = root;
			return this;
		}

		@Override public CompilationUnitResourceFileWriter packageName(@NonNull String packageName)
		{
			if (packageName   .isEmpty()) throw new IllegalArgumentException(    "package name may not be empty");
			this.packageName = packageName;
			return this;
		}

		@Override public CompilationUnitResourceFileWriter simpleFileName(@NonNull String simpleFileName)
		{
			if (simpleFileName.isEmpty()) throw new IllegalArgumentException("simple file name may not be empty");
			this.simpleFileName = simpleFileName;
			return this;
		}

		@Override public CompilationUnitResourceFileWriter fileExtension(@NonNull String fileExtension)
		{
			if (fileExtension.isEmpty()) throw new IllegalArgumentException("file extension name may not be empty");
			this.fileExtension = fileExtension;
			return this;
		}

		@Override public void write(@NonNull String output) throws IOException
		{
			Path path =
					Path.of(
							root.toString(),
							Files.toDirectoryName(packageName),
							simpleFileName + "." + fileExtension);
			writeToFile(output, path);
			log.debug("wrote {}", path.toAbsolutePath());
		}
	}
	
	class CompilationUnitResourceFileWriterSimple extends CompilationUnitResourceFileWriterAbstract
	{
		protected CompilationUnitResourceFileWriterSimple(
				@NonNull String packageName, @NonNull String simpleFileName, @NonNull String fileExtension)
		{ super(packageName, simpleFileName, fileExtension); }
	}

	static CompilationUnitResourceFileWriter create(
			@NonNull String packageName, @NonNull String simpleFileName, @NonNull String fileExtension)
			{ return new CompilationUnitResourceFileWriterSimple(packageName, simpleFileName, fileExtension); }
	static CompilationUnitResourceFileWriter writer(
			@NonNull String packageName, @NonNull String simpleFileName, @NonNull String fileExtension)
			{ return create(packageName, simpleFileName, fileExtension); }
}