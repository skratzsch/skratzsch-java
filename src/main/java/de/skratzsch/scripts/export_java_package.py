import os
import argparse

def export_java_package_to_single_file(directory, output_file):
    with open(output_file, 'w', encoding='utf-8') as outfile:
        for root, _, files in os.walk(directory):
            for file in files:
                if file.endswith('.java'):
                    file_path = os.path.join(root, file)
                    relative_path = os.path.relpath(file_path, start=directory)
                    outfile.write(f"\n// === File: {relative_path} ===\n\n")
                    with open(file_path, 'r', encoding='utf-8') as infile:
                        outfile.write(infile.read())
                    outfile.write('\n' + '='*50 + '\n')

def main():
    parser = argparse.ArgumentParser(description='Exportiert ein Java-Paket in eine einzelne Textdatei.')
    parser.add_argument('directory', type=str, help='Verzeichnis des Java-Pakets')
    args = parser.parse_args()

    output_filename = f"{os.path.basename(os.path.normpath(args.directory))}_export.txt"
    output_path = os.path.join(args.directory, output_filename)

    export_java_package_to_single_file(args.directory, output_path)
    print(f"Export erfolgreich! Die Datei wurde unter {output_path} gespeichert.")

if __name__ == "__main__":
    main()
